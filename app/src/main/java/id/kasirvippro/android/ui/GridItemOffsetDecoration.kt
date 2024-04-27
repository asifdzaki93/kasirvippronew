package id.kasirvippro.android.ui

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView


class GridItemOffsetDecoration : RecyclerView.ItemDecoration {

    private var mItemOffsetLeft: Int = 0
    private var mItemOffsetTop: Int = 0
    private var mItemOffsetRight: Int = 0
    private var mItemOffsetBottom: Int = 0

    constructor(itemOffset: Int) {
        this.mItemOffsetLeft = itemOffset
        this.mItemOffsetTop = itemOffset
        this.mItemOffsetRight = itemOffset
        this.mItemOffsetBottom = itemOffset
    }

    constructor(itemOffsetLeft: Int, itemOffsetTop: Int, itemOffsetRight: Int, itemOffsetBottom: Int) {
        mItemOffsetLeft = itemOffsetLeft
        mItemOffsetTop = itemOffsetTop
        mItemOffsetRight = itemOffsetRight
        mItemOffsetBottom = itemOffsetBottom
    }

    constructor(context: Context, @DimenRes itemOffsetId: Int) : this(context.resources.getDimensionPixelSize(itemOffsetId)) {}

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(mItemOffsetLeft, mItemOffsetTop, mItemOffsetRight, mItemOffsetBottom)
    }
}

